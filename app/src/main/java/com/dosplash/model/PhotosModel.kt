package com.dosplash.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotosModel(
    var id: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var promoted_at: String? = null,
    var width: Int = 0,
    var height: Int = 0,
    var color: String? = null,
    var description: String? = null,
    var alt_description: String? = null,
    var urls: UrlsModel? = null,
    var links: LinksModel? = null,
    var likes: Int = 0,
    var liked_by_user: Boolean = false,
    var user: UserModel? = null,
    var exif: ExifModel? = null,
    var location: LocationModel? = null,
    var views: Int = 0,
    var downloads: Int = 0
) : Parcelable

@Parcelize
data class UrlsModel(
    var raw: String? = null,
    var full: String? = null,
    var regular: String? = null,
    var small: String? = null,
    var thumb: String? = null
) : Parcelable

@Parcelize
data class LinksModel(
    var self: String? = null,
    var html: String? = null,
    var download: String? = null,
    var download_location: String? = null,
    var photos: String? = null,
    var likes: String? = null,
    var portfolio: String? = null,
    var following: String? = null,
    var followers: String? = null
) : Parcelable

@Parcelize
data class UserModel(
    var id: String? = null,
    var updated_at: String? = null,
    var username: String? = null,
    var name: String? = null,
    var first_name: String? = null,
    var last_name: String? = null,
    var twitter_username: String? = null,
    var portfolio_url: String? = null,
    var bio: String? = null,
    var location: String? = null,
    var links: LinksModel? = null,
    var profile_image: ProfileImageModel? = null,
    var instagram_username: String? = null,
    var total_collections: Int = 0,
    var total_likes: Int = 0,
    var total_photos: Int = 0,
    var accepted_tos: Boolean = false
) : Parcelable

@Parcelize
data class ProfileImageModel(
    var small: String? = null,
    var medium: String? = null,
    var large: String? = null
) : Parcelable

@Parcelize
data class ExifModel(
    var make: String? = null,
    var model: String? = null,
    var exposure_time: String? = null,
    var aperture: String? = null,
    var focal_length: String? = null,
    var iso: Int = 0
) : Parcelable

@Parcelize
data class LocationModel(
    var title: String? = null,
    var name: String? = null,
    var city: String? = null,
    var country: String? = null,
    var position: PositionModel? = null
) : Parcelable

@Parcelize
data class PositionModel(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
) : Parcelable