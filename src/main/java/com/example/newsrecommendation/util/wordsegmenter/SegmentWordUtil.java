package com.example.newsrecommendation.util.wordsegmenter;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description     分词工具类 主要还是调用依赖类
 * @Author 11103882
 * @Date 2019/9/16
 * @Version 1.0
 */
public class SegmentWordUtil {

    /**
     *        利用 word 分词项目 的分词工具
     *        速度加载太慢
     * @param word
     * @return
     */
//    public static List<String> segmentWordIntoList1(String word){
//        if (word==null){
//            return null;
//        }
//        List<Word> words = WordSegmenter.seg(word);
//        PartOfSpeechTagging.process(words);
//        List<String> wordsStrList = new ArrayList<>();
//        for (Word w : words){
//            if (wordsStrList.contains(w.toString())){
//                continue;
//            }
//            wordsStrList.add(w.toString());
//        }
//        return wordsStrList;
//    }

    /**
     *        利用hanLp 分词开源项目分词  分词不重复
     * @param word
     * @return
     */
    public static List<String> segmentWordIntoList(String word){
        if (word==null){
            return null;
       }
        List<Term> termList = StandardTokenizer.segment(word);
        List<String> wordList = new ArrayList<>();
        for (Term t :termList){
            if (wordList.contains(t.toString())){
                continue;
            }
            wordList.add(t.toString());
        }
        return wordList;
    }

    public static String getKeyword(String content){
        List<String> keywordList = HanLP.extractKeyword(content, 3);
        StringBuilder sb = new StringBuilder();
        for (String s : keywordList){
            sb.append(s+"; ");
        }
        return sb.toString();
    }

}
