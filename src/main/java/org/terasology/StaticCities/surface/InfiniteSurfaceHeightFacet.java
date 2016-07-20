/*
 * Copyright 2015 MovingBlocks
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

package org.terasology.StaticCities.surface;

import org.terasology.math.geom.BaseVector2i;
import org.terasology.world.generation.WorldFacet;

/**
 *
 */
public interface InfiniteSurfaceHeightFacet extends WorldFacet {

    default float getWorld(BaseVector2i worldPos) {
        return getWorld(worldPos.getX(), worldPos.getY());
    }

    float getWorld(int worldX, int worldY);
}