package net.lliira.vpn.model.recommend;

import java.util.List;

import net.lliira.vpn.model.Factory;
import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.user.User;

public interface RecommendationFactory extends Factory {

  void setTextFactory(TextFactory textFactory);

  Recommendation addRecommendation(User user, String url, String description);

  void updateRecommendation(User user, Recommendation recommendation,
      String url, String description);

  void deleteRecommendation(User user, Recommendation recommendation);

  void likeRecommendation(User user, Recommendation recommendation, boolean like);

  List<Recommendation> getRecommendations();

  List<Recommendation> getRecommendations(User user);

  Recommendation getRecommendation(int id);
}
