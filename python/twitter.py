import pandas as pd
import tweepy as tw
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener


consumer_key = 'GLpKby8CDuz64y12djqBSkBNh'
consumer_secret = 'vZOG1ip9B01HZcK8YK9lSKEFiwUY3TW7sFUOittr11Xo671ucx'
access_token = '2225225340-0SNCnE57fKm4vBIpBqBBL8GXOCX4efmP7nB8Loa'
access_token_secret = 'tVaM7IzcH9t4LgH1xmg3VqixLlFhw3xYzf0XNHNT6eDkN'

class Listener(StreamListener):

    def on_data(self, data):
        print(data)
        return True

    def on_error(self, status):
        print(status)

auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

def connect_new():
    twitterStream = Stream(auth, Listener())
    twitterStream

def connect():

    auth = tw.OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    api = tw.API(auth, wait_on_rate_limit=True)
    print("success")


if __name__ == "__main__":
    connect_new()