package com.mtur.lc;

public class LaserBeamsOpt {
    public int numberOfBeams(String[] bank) {
        int beamsCnt = 0;
        int prevRowLasersCnt = 0;

        for (String row : bank) {
            int currRowLasersCnt = calcLasersCount(row);

            if (currRowLasersCnt > 0) {
                beamsCnt += currRowLasersCnt * prevRowLasersCnt;
                prevRowLasersCnt = currRowLasersCnt;
            }
        }

        return beamsCnt;
    }

    private static int calcLasersCount(String row) {
        int laserCount = 0;
        for (int i = 0; i < row.length(); laserCount += row.charAt(i++) - '0');

        return laserCount;
    }
}