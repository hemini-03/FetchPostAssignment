package com.feed.apptest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostModel{
    @SerializedName("hits")
    @Expose
    var hits: MutableList<HitModel>? = null
    @SerializedName("nbHits")
    @Expose
    var nbHits: Int? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("nbPages")
    @Expose
    var nbPages: Int? = null
    @SerializedName("hitsPerPage")
    @Expose
    var hitsPerPage: Int? = null
    @SerializedName("processingTimeMS")
    @Expose
    var processingTimeMS: Int? = null
    @SerializedName("exhaustiveNbHits")
    @Expose
    var exhaustiveNbHits: Boolean? = null
    @SerializedName("query")
    @Expose
    var query: String? = null
    @SerializedName("params")
    @Expose
    var params: String? = null

    inner class HitModel {
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("author")
        @Expose
        var author: String? = null
        @SerializedName("points")
        @Expose
        var points: Int? = null
        @SerializedName("story_text")
        @Expose
        var storyText: Any? = null
        @SerializedName("comment_text")
        @Expose
        var commentText: Any? = null
        @SerializedName("num_comments")
        @Expose
        var numComments: Int? = null
        @SerializedName("story_id")
        @Expose
        var storyId: Any? = null
        @SerializedName("story_title")
        @Expose
        var storyTitle: Any? = null
        @SerializedName("story_url")
        @Expose
        var storyUrl: Any? = null
        @SerializedName("parent_id")
        @Expose
        var parentId: Any? = null
        @SerializedName("created_at_i")
        @Expose
        var createdAtI: Int? = null
        @SerializedName("relevancy_score")
        @Expose
        var relevancyScore: Int? = null
        @SerializedName("_tags")
        @Expose
        var tags: List<String>? = null
        @SerializedName("objectID")
        @Expose
        var objectID: String? = null
        @SerializedName("_highlightResult")
        @Expose
        var highlightResult: HighlightResultModel? = null
    }

    inner class HighlightResultModel {

        @SerializedName("title")
        @Expose
        var title: TitleModel? = null
        @SerializedName("author")
        @Expose
        var author: AuthorModel? = null
        @SerializedName("story_text")
        @Expose
        var storyText: StoryTextModel? = null
        @SerializedName("url")
        @Expose
        var url: UrlModel? = null

    }

    inner class TitleModel {

        @SerializedName("value")
        @Expose
        var value: String? = null
        @SerializedName("matchLevel")
        @Expose
        var matchLevel: String? = null
        @SerializedName("matchedWords")
        @Expose
        var matchedWords: List<Any>? = null

    }

    inner class AuthorModel {

        @SerializedName("value")
        @Expose
        var value: String? = null
        @SerializedName("matchLevel")
        @Expose
        var matchLevel: String? = null
        @SerializedName("matchedWords")
        @Expose
        var matchedWords: List<Any>? = null

    }

    inner class StoryTextModel {

        @SerializedName("value")
        @Expose
        var value: String? = null
        @SerializedName("matchLevel")
        @Expose
        var matchLevel: String? = null
        @SerializedName("matchedWords")
        @Expose
        var matchedWords: List<Any>? = null

    }

    inner class UrlModel {

        @SerializedName("value")
        @Expose
        var value: String? = null
        @SerializedName("matchLevel")
        @Expose
        var matchLevel: String? = null
        @SerializedName("matchedWords")
        @Expose
        var matchedWords: List<Any>? = null

    }

}