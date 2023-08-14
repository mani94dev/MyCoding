package com.pluralsight.streamlambda;

import java.math.BigInteger;
import java.util.UUID;
import java.util.stream.Stream;

public class AdvancedStreamsExample01 {

  public static void main(String[] args) {
    // random uuids
    Stream<UUID> uuids = Stream.generate(UUID::randomUUID);
    uuids.limit(10).forEach(System.out::println);

    // power of 2
    Stream<BigInteger> powerOfTwo = Stream.iterate(BigInteger.ONE, n -> n.multiply(BigInteger.TWO));
    powerOfTwo.limit(10).forEach(System.out::println);

    // last two fibonacci numbers
    Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
        .limit(10)
        .forEach(f -> System.out.println(f[0]));

  }

}
