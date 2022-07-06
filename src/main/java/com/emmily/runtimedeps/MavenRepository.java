package com.emmily.runtimedeps;

import com.emmily.runtimedeps.connection.HTTPConnectionFactory;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MavenRepository {

  public static final MavenRepository MAVEN_CENTRAL = new MavenRepository(
    "central",
    "https://repo1.maven.org/maven2/"
  );

  private final String id;
  private final String url;

  public MavenRepository(String id,
                         String url) {
    this.id = id;
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public boolean requiresAuth() {
    HttpURLConnection connection = HTTPConnectionFactory.createConnection(
      url,
      "runtime-deps"
    );

    try {
      int responseCode = connection.getResponseCode();

      return responseCode == 401 || responseCode == 403;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return "MavenRepository{" +
      "id='" + id + '\'' +
      ", url='" + url + '\'' +
      '}';
  }
}