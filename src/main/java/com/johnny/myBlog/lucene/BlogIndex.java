package com.johnny.myBlog.lucene;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.util.DateUtil;
import com.johnny.myBlog.util.StringFormatUtil;

/**
 * 使用lucene对博客crdu
 * @author johnny
 *
 */
public class BlogIndex {
	private Directory dir = null;
	private String lucenePath = "D://lucene";
	
	/**
	 * 获取对lucene的写入方法
	 * @throws IOException 
	 */
	private IndexWriter getWriter() throws IOException {
		dir = FSDirectory.open(Paths.get(lucenePath, new String[0]));
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir,iwc);
		return writer;
	}
	/**
	 * 增加索引
	 * @throws IOException 
	 */
	public void addIndex(Blog blog) throws IOException {
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		doc.add(new TextField("content",blog.getContentWithoutTag(),Field.Store.YES));
		doc.add(new TextField("keyWord",blog.getKeyWord(),Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
	}
	/**
	 * 更新索引
	 * @throws IOException 
	 */
	public void updateIndex(Blog blog) throws IOException {
		IndexWriter writer = getWriter();
		Document doc =  new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		doc.add(new TextField("content",blog.getContentWithoutTag(),Field.Store.YES));
		doc.add(new TextField("keyWord",blog.getKeyWord(),Field.Store.YES));
		//以id为主键
		writer.updateDocument(new Term("id",String.valueOf(blog.getId())), doc);
		writer.close();
	}
	/**
	 * 删除索引
	 * @throws IOException 
	 */
	public void deleteIndex(String blogId) throws IOException {
		IndexWriter writer = getWriter();
		writer.deleteDocuments(new Term[] {new Term("id",blogId)});
		writer.forceMergeDeletes();
		writer.commit();
		writer.close();
	}
	/**
	 * 搜索索引
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	public List<Blog> searchBlog(String q) throws IOException, ParseException, InvalidTokenOffsetsException{
		List<Blog> blogList = new LinkedList();
		dir = FSDirectory.open(Paths.get(lucenePath, new String[0]));
		//获取reader
		IndexReader reader = DirectoryReader.open(dir);
		//获取流
		IndexSearcher is = new IndexSearcher(reader);
		//放入查询条件
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		QueryParser parser = new QueryParser("title",analyzer);
		Query query = parser.parse(q);
		QueryParser parser2 = new QueryParser("content",analyzer);
		Query query2 = parser.parse(q);
		QueryParser parser3 = new QueryParser("keyWord",analyzer);
		Query query3 = parser.parse(q);
		
		booleanQuery.add(query,BooleanClause.Occur.SHOULD);
		booleanQuery.add(query2,BooleanClause.Occur.SHOULD);
		booleanQuery.add(query3,BooleanClause.Occur.SHOULD);
		//最多返回100条数据
		TopDocs hits = is.search(booleanQuery.build(), 100);
		//高亮搜索字
		QueryScorer scorer = new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter,scorer);
		highlighter.setTextFragmenter(fragmenter);
		//遍历查询结果,放入blogList
		for(ScoreDoc scoreDoc:hits.scoreDocs) {
			Document doc = is.doc(scoreDoc.doc);
			Blog blog = new Blog();
			blog.setId(Integer.parseInt(doc.get("id")));
			blog.setReleaseDateStr(doc.get("releaseDate"));
			String title = doc.get("title");
			String content = StringEscapeUtils.escapeHtml(doc.get("content"));
			String keyWord = doc.get("keyWord");
			if(title!=null) {
				TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
				String hTitle = highlighter.getBestFragment(tokenStream, title);
				if(StringFormatUtil.isEmpty(hTitle)) {
					blog.setTitle(title);
				}else {
					blog.setTitle(hTitle);
				}
			}
			if(content!=null) {
				TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
				String hContent = highlighter.getBestFragment(tokenStream, content);
				if(StringFormatUtil.isEmpty(hContent)) {
					if(content.length()<=200) {
						blog.setContent(content);
					}else {
						blog.setContent(content.substring(0,200));
					}
					
				}else {
					blog.setContent(hContent);
				}
			}
			if(keyWord!=null) {
				TokenStream tokenStream = analyzer.tokenStream("keyWord", new StringReader(keyWord));
				String hKeyWord = highlighter.getBestFragment(tokenStream, keyWord);
				if(StringFormatUtil.isEmpty(hKeyWord)) {
					blog.setKeyWord(keyWord);
				}else {
					blog.setKeyWord(hKeyWord);
				}
			}
			blogList.add(blog);
		}
		
		return blogList;
	}
}
