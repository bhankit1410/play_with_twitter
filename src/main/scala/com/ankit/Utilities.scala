package com.ankit

import org.apache.log4j.Level
import java.util.regex.Pattern
import java.util.regex.Matcher
import twitter4j.TwitterFactory
import twitter4j.Twitter
import twitter4j.conf.ConfigurationBuilder
import scala.io._
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object Utilities {

  val path_to_key_json = "/Users/ankitbhardwaj/IdeaProjects/play_with_twitter/secrets/twitter_api_keys.json"
  val path_to_key_csv = "/Users/ankitbhardwaj/IdeaProjects/play_with_twitter/secrets/keys.csv"

  /** Makes sure only ERROR messages get logged to avoid log spam. */
  def setupLogging() = {
    import org.apache.log4j.{Level, Logger}   
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)   
  }


  def readApiKeys(): Map[String, Object] ={
    val json = Source.fromFile(path_to_key_json)

    // parse
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    val parsedJson = mapper.readValue[Map[String, Object]](json.reader())
    parsedJson
  }
  /** Configures Twitter service credentials using twiter.txt in the main workspace directory */
  def setupTwitter() = {
    import scala.io.Source

    for (line <- Source.fromFile(path_to_key_csv).getLines) {
      val fields = line.split(",")

      if (fields.length == 2) {
        System.setProperty("twitter4j.oauth." + fields(0), fields(1))
      }

      /** another implementation
       * //  val fields = readApiKeys()
       * //  val cb = new ConfigurationBuilder()
       * //  cb.setDebugEnabled(true)
       * //      .setOAuthConsumerKey(fields("AuthConsumerKey").toString)
       * //      .setOAuthConsumerSecret(fields("AuthConsumerSecret").toString)
       * //      .setOAuthAccessToken(fields("AuthAccessToken").toString)
       * //      .setOAuthAccessTokenSecret(fields("AuthAccessTokenSecret").toString)
       * //    val tf = new TwitterFactory(cb.build())
       * //    val twitter = tf.getInstance()
       * //    twitter
       */
    }
  }

  
  /** Retrieves a regex Pattern for parsing Apache access logs. */
  def apacheLogPattern():Pattern = {
    val ddd = "\\d{1,3}"                      
    val ip = s"($ddd\\.$ddd\\.$ddd\\.$ddd)?"  
    val client = "(\\S+)"                     
    val user = "(\\S+)"
    val dateTime = "(\\[.+?\\])"              
    val request = "\"(.*?)\""                 
    val status = "(\\d{3})"
    val bytes = "(\\S+)"                     
    val referer = "\"(.*?)\""
    val agent = "\"(.*?)\""
    val regex = s"$ip $client $user $dateTime $request $status $bytes $referer $agent"
    Pattern.compile(regex)    
  }
}