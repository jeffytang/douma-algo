package com.douma.line.algo.bs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class IpLocationParser {
    private static class IpLocation {
        public long startIp;
        public long endIp;
        public String locationCity;
    }
    private static ArrayList<IpLocation> sortedIpLocations = new ArrayList<>();
    static {
        try {
            // 1. 读取文件，解析 ip 地址段
            BufferedReader reader =
                    new BufferedReader(new FileReader("data\\ip_location.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] temps = line.split(" ");
                IpLocation ipLocation = new IpLocation();
                ipLocation.startIp = ip2Score(temps[0]);
                ipLocation.endIp = ip2Score(temps[1]);
                ipLocation.locationCity = temps[2];
                sortedIpLocations.add(ipLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("解析 ip 地址库出错" + e);
        }

        // 2. 按照起始 ip 进行升序排列
        // 时间复杂度：O(nlogn)
        Collections.sort(sortedIpLocations, new Comparator<IpLocation>() {
            @Override
            public int compare(IpLocation o1, IpLocation o2) {
                if (o1.startIp < o2.startIp) return -1;
                else if (o1.startIp > o2.startIp) return 1;
                else return 0;
            }
        });
    }

    // 将ip转成长整型
    public static Long ip2Score(String ip) {
        String[] temps = ip.split("\\.");
        Long score = 256 * 256 * 256 * Long.parseLong(temps[0])
                + 256 * 256 * Long.parseLong(temps[1])
                + 256 * Long.parseLong(temps[2])
                + Long.parseLong(temps[3]);
        return score;
    }

    // 二分查找指定 ip 对应的城市
    // 时间复杂度：O(logn)
    public static String getIpLocation(String ip) {
        long score = ip2Score(ip);
        // 3. 在 sortedIpLocations 中找到最后一个 startIp 小于等于 score 的这个 ip 段
        int left = 0;
        int right = sortedIpLocations.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (score >= sortedIpLocations.get(mid).startIp) {
                if (mid == sortedIpLocations.size() - 1
                        || sortedIpLocations.get(mid + 1).startIp > score) {
                    if (score <= sortedIpLocations.get(mid).endIp) {
                        return sortedIpLocations.get(mid).locationCity;
                    }
                } else {
                    left = mid + 1;
                }
            } else { // target < nums[mid]
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getIpLocation("202.101.48.198"));
    }
}
