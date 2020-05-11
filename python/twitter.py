import pandas as pd
import tweepy as tw
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
import json


key_path = "/Users/ankitbhardwaj/PycharmProjects/play_with_twitter/secrets/twitter_api_keys.json"

with open(key_path, "r") as read_file:
    keys = json.load(read_file)[0]
# keys = pd.read_json("../secrets/twitter_api_keys")
consumer_key = keys["AuthConsumerKey"]
consumer_secret = keys['AuthConsumerSec']
access_token = keys['AuthAccessToken']
access_token_secret = keys['AuthAccessTokenSecret']

class Listener(StreamListener):

    def on_data(self, data):
        print(data)
        return True

    def on_error(self, status):
        print(status)



def connect():

    auth = tw.OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    api = tw.API(auth, wait_on_rate_limit=True)
    print("success")
    places = api.geo_search(query="INDIA", granularity="country")
    place_id = places[0].id

    tweets = api.search(q="place:%s" % place_id)
    for tweet in tweets:
        print(tweet.text + " | " + tweet.place.name if tweet.place else "Undefined place")


if __name__ == "__main__":
    connect()