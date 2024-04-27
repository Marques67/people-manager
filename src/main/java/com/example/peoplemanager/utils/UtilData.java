package com.example.peoplemanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilData {

    public static String formatDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
