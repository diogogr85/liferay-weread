package com.diogo.weread.features.data.source.local

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.models.FeedItem
import com.diogo.weread.data.models.FeedWrong
import com.diogo.weread.data.source.local.database.FeedsDao
import com.diogo.weread.data.source.local.database.WeReadDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class WeReadDaosTest {

    private lateinit var dao: FeedsDao
    private lateinit var db: WeReadDataBase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                WeReadDataBase::class.java).build()
        dao = db.feedsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAnReadFeed() {
        val feed = Feed(1, "12345", "test title", "http://test.com",
                "general","url","12/12/1989", ArrayList<FeedItem>())
        dao.insertFeed(feed)

        dao.getFeeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            assertThat(it[0], equalTo(feed));
                        },
                        {
                            it.printStackTrace()
                        }
                )
    }

}