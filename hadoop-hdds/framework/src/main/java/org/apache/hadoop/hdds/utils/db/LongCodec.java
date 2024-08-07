/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.hadoop.hdds.utils.db;

import com.google.common.primitives.Longs;

import javax.annotation.Nonnull;
import java.util.function.IntFunction;

/**
 * Codec to convert Long to/from byte array.
 */
public final class LongCodec implements Codec<Long> {
  @Override
  public boolean supportCodecBuffer() {
    return true;
  }

  @Override
  public CodecBuffer toCodecBuffer(@Nonnull Long object,
      IntFunction<CodecBuffer> allocator) {
    return allocator.apply(Long.BYTES).putLong(object);
  }

  @Override
  public Long fromCodecBuffer(@Nonnull CodecBuffer buffer) {
    return buffer.asReadOnlyByteBuffer().getLong();
  }

  @Override
  public byte[] toPersistedFormat(Long object) {
    if (object != null) {
      return Longs.toByteArray(object);
    } else {
      return null;
    }
  }

  @Override
  public Long fromPersistedFormat(byte[] rawData) {
    if (rawData != null) {
      return Longs.fromByteArray(rawData);
    } else {
      return null;
    }
  }

  @Override
  public Long copyObject(Long object) {
    return object;
  }
}
