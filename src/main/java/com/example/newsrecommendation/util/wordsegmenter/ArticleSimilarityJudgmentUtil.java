package com.example.newsrecommendation.util.wordsegmenter;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/17
 * @Version 1.0
 */
public class ArticleSimilarityJudgmentUtil {

    /**
     *        判断文章相似度  通过 已经生成的词向量模型 来判断文章的相似度，返回相似度百分比
     * @param originArticle
     * @param goalArtical
     * @return
     */
    public static float getSimilaritySocre(String originArticle,String goalArtical){
        DocVectorModel docVectorModel =null;
        try {
             docVectorModel = new DocVectorModel(
                    new WordVectorModel("E:\\workspace\\PersonalProject\\newsrecommendation\\src\\main\\resources\\data\\msr_vectors.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docVectorModel.similarity(originArticle, goalArtical);
    }

    /**
     * 训练词向量模型
     *
     */
    private void trainModel(){
        Word2VecTrainer trainerBuilder = new Word2VecTrainer();
        WordVectorModel wordVectorModel = trainerBuilder.train("E:\\workspace\\PersonalProject\\newsrecommendation" +
                "\\src\\main\\resources\\data\\msr_training.utf8",
                "E:\\workspace\\PersonalProject\\newsrecommendation\\" +
                        "src\\main\\resources\\data\\msr_vectors.txt");
    }
}

