package com.example.senthil.kotlin_tablayout

import android.os.Parcel
import android.os.Parcelable

class Consumption constructor(var id: Int?, var money: String?, var usage: String?, var time: String?) : Parcelable {

    constructor(parcel: Parcel?) : this (
            parcel?.readInt(),
            parcel?.readString(),
            parcel?.readString(),
            parcel?.readString()
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(id!!)
        parcel?.writeString(money)
        parcel?.writeString(usage)
        parcel?.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Consumption> {
        override fun createFromParcel(parcel: Parcel?): Consumption {
            return Consumption(parcel)
        }

        override fun newArray(size: Int): Array<Consumption?> {
            return arrayOfNulls(size)
        }
    }
}