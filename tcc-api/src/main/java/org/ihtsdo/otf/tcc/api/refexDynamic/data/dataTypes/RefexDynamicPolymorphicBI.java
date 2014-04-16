/*
 * Copyright 2010 International Health Terminology Standards Development Organisation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ihtsdo.otf.tcc.api.refexDynamic.data.dataTypes;

import org.ihtsdo.otf.tcc.api.refexDynamic.data.RefexDynamicDataBI;

/**
 * 
 * {@link RefexDynamicPolymorphicBI}
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
public interface RefexDynamicPolymorphicBI extends RefexDynamicDataBI
{
	//Polymorphic is a noop, which should never actually exist for data storage.  It is only used in the definition of 
	//Refex types when the actual data type is not known ahead of time.
}