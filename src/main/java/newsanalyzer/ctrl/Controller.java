package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "f7175e033d9044b19b4b44c38cc34a94";

	public void process(NewsApi news) throws NewsAnalyserException, BuildUrlException, IOException {
		System.out.println("Start process");

		NewsReponse newsResponse = news.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));

			System.out.println("Length");
			getTitlesSortedByLength(articles).forEach(article -> System.out.println(article.getTitle()));

			System.out.println("Shortest Artist");
			getShortestAuthorName(articles).forEach(article -> System.out.println(article.getAuthor()));

			System.out.println("Number of articles");
			System.out.println(getNumberOfArticle(articles));

			System.out.println("Best Provider");
			System.out.println(getBestProvider(articles));
		}



		System.out.println("End process");
	}

	public Long getNumberOfArticle(List<Article> data) {
		return (long) data.size();

	}
	public List<Article> getTitlesSortedByLength(List<Article> data) {
		return data
				.stream()
				.sorted(Comparator.comparing(Article::getTitle))
				.collect(Collectors.toList());
	}

	public String getBestProvider(List<Article> data) {
		return data
				.stream()
				.collect(Collectors.groupingBy(article ->article.getSource().getName(),Collectors.counting()))
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue()).orElseThrow(NoSuchElementException::new).getKey();
	}
	public List<Article> getShortestAuthorName(List <Article> data) {
		return data
				.stream()
				.filter(article -> Objects.nonNull(article.getAuthor()))
				.sorted(Comparator.comparing(Article::getAuthor))
				.collect(Collectors.toList());

	}
	public Object getData() {

		return null;
	}
}
