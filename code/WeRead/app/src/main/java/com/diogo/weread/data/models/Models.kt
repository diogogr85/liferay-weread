package com.diogo.weread.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.*


data class Auth(
        val email: String,
        val password: String
)

data class Session(
        @SerializedName("access_token")
        val accessToken: String,
        @SerializedName("refresh_token")
        val refreshToken: String,
        val scope: String,
        @SerializedName("expires_in")
        val expiresIn: String,
        @SerializedName("token_type")
        val tokenType: String
)

data class User(
        val id: String? = null,
        val name: String,
        val email: String
)

@Entity(tableName = "feed")
data class Feed(
        @PrimaryKey(autoGenerate = true)
        val tableId: Int? = null,
        @SerializedName("id")
        val weDeployId: String? = null,
        val title: String?,
        val description: String?,
        val feedImageUrl: String?,
        val category: String?,
        val url: String?,
        val userId: String?,
        val feedItem: List<FeedItem>?
)

@Entity(tableName = "feed_item")
data class FeedItem(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val title: String?,
        val description: String?,
        val thumbnail: String? = null,
        val url: String?
)

@Root(name = "rss", strict = false)
data class Rss @JvmOverloads constructor(
        @field:Element(name = "channel")
        var channel: Channel? = null
)

@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(
        @field:Element(name = "title")
        var title: String? = null,
        @field:ElementList(inline = true, required = false)
        var items: List<Item>? = null,
        @field:Element(name = "description", required = false)
        var description: String? = null,
        @field:Element(name = "image", required = false)
        var channelIcon: ChannelImage? = null
)

@Root(name = "item", strict = false)
data class Item @JvmOverloads constructor(
        @field:Element(name = "title")
        var title: String? = null,
        @field:Element(name = "link")
        var link: String? = null,
        @field:Element(name = "description", required = false)
        var description: String? = null
)

@Root(name = "image", strict = false)
data class ChannelImage @JvmOverloads constructor(
        @field:Element(name = "url", required = false)
        var url: String? = null
)

