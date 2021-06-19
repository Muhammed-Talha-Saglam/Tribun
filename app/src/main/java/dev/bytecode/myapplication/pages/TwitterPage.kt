package dev.bytecode.myapplication


import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.*
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel


@Composable
fun MakeTwitterPage(viewModel: DatabaseViewModel, activity: Activity) {

    val followingAuthors by viewModel.followingAuthors.observeAsState()
    viewModel.getCurrentUser()


    
    // Provide your own Twitter Api Keys
    val config = TwitterConfig.Builder(activity)
        .logger(DefaultLogger(Log.DEBUG))
        .twitterAuthConfig(
            TwitterAuthConfig(
                "********************",
                "********************"
            )
        )
        .debug(true)
        .build()

    Twitter.initialize(config)


    followingAuthors?.let { authorList ->

        for (author in authorList) {

            val userTimeline = UserTimeline.Builder()
                .includeReplies(true)
                .includeRetweets(true)
                .screenName(author.twitterUserName)
                .maxItemsPerRequest(20)
                .build()



            userTimeline.next(null, object : Callback<TimelineResult<Tweet>>() {

                override fun success(result: Result<TimelineResult<Tweet>>?) {
                    if (result != null) {

                        Log.d("result.data.items", result.data.items.toString())

                        result.data.items.forEach {
                            Log.d("forEach", it.toString())

                            viewModel.loadTweets(it)

                        }

                    }
                }

                override fun failure(exception: TwitterException?) {

                }
            })

        }


        makeListOfTweets(viewModel, activity)



    }


}


@Composable
fun makeListOfTweets(viewModel: DatabaseViewModel, activity: Activity) {

    val list by viewModel.listOfTweets.observeAsState()

    var adapter: TweetTimelineRecyclerViewAdapter

    val fixedTimeLine = FixedTweetTimeline.Builder().setTweets(list?.toList()).build()


    adapter = TweetTimelineRecyclerViewAdapter.Builder(activity)
        .setTimeline(fixedTimeLine)
        .build()




    Box(
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.Center
    ) {


        CircularProgressIndicator(
            color = Color.Black
        )


        AndroidView(viewBlock = ::RecyclerView) { rec ->
            rec.adapter = adapter
            rec.layoutManager = LinearLayoutManager(activity)

        }

    }

}
