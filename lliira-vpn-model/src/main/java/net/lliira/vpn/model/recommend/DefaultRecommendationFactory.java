package net.lliira.vpn.model.recommend;

import java.util.List;

import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.user.User;

public class DefaultRecommendationFactory implements RecommendationFactory {
  
  private TextFactory textFactory;

  public DefaultRecommendationFactory() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void setTextFactory(TextFactory textFactory) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Recommendation addRecommendation(User user, String url,
      String description) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void updateRecommendation(User user, Recommendation recommendation,
      String url, String description) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteRecommendation(User user, Recommendation recommendation) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void likeRecommendation(User user, Recommendation recommendation,
      boolean like) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Recommendation> getRecommendations() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Recommendation> getRecommendations(User user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Recommendation getRecommendation(int id) {
    // TODO Auto-generated method stub
    return null;
  }

}
