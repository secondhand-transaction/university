package utils;

import java.io.IOException;

import java.util.Set;

import com.ansj.vec.domain.WordEntry;

import me.xiaosheng.word2vec.Word2Vec;

public class Word2VecTest {
	public static void main(String[] args) throws InterruptedException {
		test();
	}
	
	public static void test() throws InterruptedException {
		Word2Vec w2c = new Word2Vec();
		
		final String path = "C:\\Baidu\\wiki_chinese_word2vec(Google).model";
		
		try {
			w2c.loadGoogleModel(path);
			System.out.println("等待。。。。。。");
//			Thread.currentThread();
//			Thread.sleep(30000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		float[] diannao = w2c.getWordVector("笔记本电脑");
//		
//		
//		for(int i = 0; i < diannao.length; i++) {
//			System.out.print(diannao[i]);
//		}
//		
		System.out.println(w2c.wordSimilarity("沙发", "真皮沙发"));
		Set<WordEntry> sw = w2c.getSimilarWords("沙发", 10);
		
		for(WordEntry w : sw) {
			System.out.println(w.name + ":" + w.score);
		}
		System.out.println("this end");
	}
}
