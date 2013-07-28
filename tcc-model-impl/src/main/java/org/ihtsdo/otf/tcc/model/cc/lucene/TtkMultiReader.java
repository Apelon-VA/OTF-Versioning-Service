/*
 * Copyright 2013 International Health Terminology Standards Development Organisation.
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
package org.ihtsdo.otf.tcc.model.cc.lucene;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;

/**
 *
 * @author kec
 */
public class TtkMultiReader extends MultiReader {

    public TtkMultiReader(IndexReader... subReaders) {
        super(subReaders);
    }

    public TtkMultiReader(IndexReader[] subReaders, boolean closeSubReaders) {
        super(subReaders, closeSubReaders);
    }
    
    public boolean isFirstIndex(int docID) {
        return readerIndex(docID) == 0;
    }
}
