package com.algebra.aspect.kafka.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @author al
 * @date 2019/7/11 16:00
 * @description
 */
public class LineSplit {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-linesplit");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        final StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> source = builder.stream("streams-plaintext-input");
        source.flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                .to("streams-linesplit-output");

//        Sub-topologies:
//        Sub-topology: 0
//        Source: KSTREAM-SOURCE-0000000000(topics: streams-plaintext-input) --> KSTREAM-FLATMAPVALUES-0000000001
//        Processor: KSTREAM-FLATMAPVALUES-0000000001(stores: []) --> KSTREAM-SINK-0000000002 <-- KSTREAM-SOURCE-0000000000
//        Sink: KSTREAM-SINK-0000000002(topic: streams-linesplit-output) <-- KSTREAM-FLATMAPVALUES-0000000001
//        Global Stores:
//        none
        /* 新的处理器节点KSTREAM-FLATMAPVALUES-0000000001被注入到原始源节点和宿节点之间的拓扑中。
        它将源节点作为其父节点，将sink节点作为其子节点。
        换句话说，源节点提取的每个记录将首先遍历到新添加的KSTREAM-FLATMAPVALUES-0000000001节点以进行处理，结果将生成一个或多个新记录。
        它们将继续遍历到汇聚节点以写回Kafka。
        请注意，此处理器节点是“无状态”的，因为它与任何存储（即(stores: [])）无关。 */


        final Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);

        // ... same as Pipe.java above
    }
}
