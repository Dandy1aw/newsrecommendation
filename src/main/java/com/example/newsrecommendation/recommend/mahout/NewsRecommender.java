package com.example.newsrecommendation.recommend.mahout;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description        基于mahout实现的物品协同过滤推荐算法用于推荐新闻
 * @Author 11103882
 * @Date 2019/9/10
 * @Version 1.0
 */
@Component
public class NewsRecommender {

	@Autowired
	DataSource dataSource;

	/*    *//**
			*       基于用户的协同过滤推荐
			* @param userID
			* @param size
			* @return
			* @throws TasteException
			*//*
				 * public List<Long> userBasedRecommender(long userID,int size) throws
				 * TasteException {
				 * UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel );
				 * NearestNUserNeighborhood neighbor = new
				 * NearestNUserNeighborhood(NEIGHBORHOOD_NUM, similarity, dataModel );
				 * Recommender recommender = new CachingRecommender(new
				 * GenericUserBasedRecommender(dataModel , neighbor, similarity));
				 * List<RecommendedItem> recommendations = recommender.recommend(userID, size);
				 * return getRecommendedItemIDs(recommendations);
				 * }
				 */

	/**
	 *        基于物品的协同过滤
	 * @param userID
	 * @param size
	 * @return
	 * @throws TasteException
	 */
	public List<Long> itemBasedRecommender(long userID, int size) throws TasteException {
		DataModel dataModel = new MySQLJDBCDataModel(dataSource, "rating", "user_id", "news_id", "rating", "ratetime");
		ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
		Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
		List<RecommendedItem> recommendations = recommender.recommend(userID, size);
		return getRecommendedItemIDs(recommendations);
	}

	/**
	 *        根据推荐Item --recommendations 是一个列表，列表中元素是map,一个包含(id,score)的map
	 * @param recommendations
	 * @return
	 */
	private List<Long> getRecommendedItemIDs(List<RecommendedItem> recommendations) {
		List<Long> recommendItems = new ArrayList<>();
		for (int i = 0; i < recommendations.size(); i++) {
			RecommendedItem recommendedItem = recommendations.get(i);
			recommendItems.add(recommendedItem.getItemID());
		}
		return recommendItems;
	}

}
