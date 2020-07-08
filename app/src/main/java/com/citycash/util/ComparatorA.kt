package com.citycash.util

import com.citycash.model.DataItem

class ComparatorA : Comparator<DataItem> {
    override fun compare(o1: DataItem?, o2: DataItem?): Int {
        if (o1 == null || o2 == null) {
            return 0
        }

        if (o2.sortProps == null || o1.sortProps == null) {
            return 0
        }
        return o1.sortProps.A?.let { o2.sortProps.A?.compareTo(it) }!!

    }
}

class ComparatorB : Comparator<DataItem> {
    override fun compare(o1: DataItem?, o2: DataItem?): Int {
        if (o1 == null || o2 == null) {
            return 0
        }

        if (o2.sortProps == null || o1.sortProps == null) {
            return 0
        }
        return o1.sortProps.B?.let { o2.sortProps.B?.compareTo(it) }!!

    }
}

class ComparatorC : Comparator<DataItem> {
    override fun compare(o1: DataItem?, o2: DataItem?): Int {
        if (o1 == null || o2 == null) {
            return 0
        }

        if (o2.sortProps == null || o1.sortProps == null) {
            return 0
        }
        return o1.sortProps.C?.let { o2.sortProps.C?.compareTo(it) }!!

    }
}
