/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyBenchmark {
    private static BigDecimal k1 = BigDecimal.valueOf(2.7);
    private static BigDecimal k2 = BigDecimal.valueOf(1.6);
    private static BigDecimal k3 = BigDecimal.valueOf(1.5);
    private static BigDecimal k4 = BigDecimal.valueOf(2.1);
    private static BigDecimal k5 = BigDecimal.valueOf(1.5);


    private static int ops = 100;

    @Benchmark
    public void doubles() {


        double price = 100.5;
        double qty = 50.4;
        double factor = 15.1;
        double sum = 0;
        for (int i= 1; i <= ops; i++) {
//            price *= i / 2.7;
//            qty *= i / 1.6;
//            factor *= i / 1.5;

            double result = price * qty / factor;
            double fxRate = 0;
            if (result > 1) {
                fxRate = result / 2.1;
            } else {
                fxRate = result / 1.5;
            }
            sum += fxRate;
        }
    }

    @Benchmark
    public void bigDecimals() {

        BigDecimal price = BigDecimal.valueOf(100.5);
        BigDecimal qty = BigDecimal.valueOf(50.4);
        BigDecimal factor = BigDecimal.valueOf(15.1);


        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 1; i < ops; i++) {
//            BigDecimal iBg = BigDecimal.valueOf(i);
//            price = price.multiply(iBg.divide(k1, 15, RoundingMode.HALF_UP));
//            qty = qty.multiply(iBg.divide(k2, 15, RoundingMode.HALF_UP));
//            factor = factor.multiply(iBg.divide(k3, 15, RoundingMode.HALF_UP));

            BigDecimal result = price.multiply(qty).divide(factor, 15, RoundingMode.HALF_UP);
            BigDecimal fxRate;
            if (result.compareTo(BigDecimal.ONE) > 0) {
                fxRate = result.divide(k4, 15, RoundingMode.HALF_UP);
            } else {
                fxRate = result.divide(k5, 15, RoundingMode.HALF_UP);
            }

            sum.add(fxRate);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
