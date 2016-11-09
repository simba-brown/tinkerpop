/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.tinkerpop.gremlin.tinkergraph.g2;

import org.apache.tinkerpop.gremlin.jsr223.JavaTranslator;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory;
import org.junit.Test;

import java.io.File;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class g2ParserTest {

    @Test
    public void shouldParse() {
        final GraphTraversalSource g = TinkerFactory.createModern().traversal();
        for (int i = 2; i < 6; i++) {
            final g2Parser parser = new g2Parser(new File("/Users/marko/Desktop/g" + i + ".txt"));
            final Traversal.Admin<?, ?> traversal = JavaTranslator.of(g).translate(parser.getBytecode());
            //System.out.println(parser.getTraversalSource());
            System.out.println("// 2g source\n");
            System.out.println(parser);
            System.out.println("\n// Gremlin bytecode");
            System.out.println(traversal.getBytecode());
            System.out.println("\n// Gremlin machine code");
            System.out.println(traversal);
            traversal.applyStrategies();
            System.out.println("\n// Optimized machine code");
            System.out.println(traversal);
            System.out.println("\n// Result");
            System.out.println(traversal.toList());
        }
    }
}