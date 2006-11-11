/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.pool.composite;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolUtils;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * Configures the behavior of the pool when the pool is out of idle objects.
 *
 * @see CompositeObjectPoolFactory#setExhaustionPolicy(ExhaustionPolicy)
 * @see CompositeKeyedObjectPoolFactory#setExhaustionPolicy(ExhaustionPolicy)
 * @author Sandy McArthur
 * @since Pool 2.0
 * @version $Revision$ $Date$
 */
/* XXX For Pool 3: public enum ExhaustionPolicy {GROW, FAIL} but keep existing Javadoc. */
public final class ExhaustionPolicy implements Serializable {

    private static final long serialVersionUID = -4895490364329810018L;

    /**
     * Grow the pool when all idle objects have been exhausted.
     */
    public static final ExhaustionPolicy GROW = new ExhaustionPolicy("GROW");

    /**
     * Throw a <code>NoSuchElementException</code> when all idle objects have been exhausted. Clients of the pool must
     * call {@link ObjectPool#addObject()} to prefill the pool.
     * @see NoSuchElementException
     * @see PoolUtils#prefill
     */
    public static final ExhaustionPolicy FAIL = new ExhaustionPolicy("FAIL");

    /**
     * enum name.
     */
    private final String name;

    private ExhaustionPolicy(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    // Autogenerated with Java 1.5 enums
    public static ExhaustionPolicy[] values() {
        return new ExhaustionPolicy[] {FAIL, GROW};
    }

    // necessary for serialization
    private static int nextOrdinal = 0;
    private final int ordinal = nextOrdinal++;
    private Object readResolve() throws ObjectStreamException {
        return values()[ordinal];
    }
}