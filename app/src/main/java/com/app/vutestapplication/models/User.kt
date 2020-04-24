package com.app.vutestapplication.models
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class User(
    @SerializedName("ad")
    val resAd: Ad? = null,
    @SerializedName("data")
    val resData: List<Data?>? = null,
    @SerializedName("page")
    val resPage: Int? = null,
    @SerializedName("per_page")
    val resPerPage: Int? = null,
    @SerializedName("total")
    val resTotal: Int? = null,
    @SerializedName("total_pages")
    val resTotalPages: Int? = null
): Serializable

data class Ad(
    @SerializedName("company")
    val resCompany: String? = null,
    @SerializedName("text")
    val resText: String? = null,
    @SerializedName("url")
    val resUrl: String? = null
): Serializable

data class Data(
    @SerializedName("avatar")
    val resAvatar: String? = null,
    @SerializedName("email")
    val resEmail: String? = null,
    @SerializedName("first_name")
    val resFirstName: String? = null,
    @SerializedName("id")
    val resId: Int? = null,
    @SerializedName("last_name")
    val resLastName: String? = null
): Serializable