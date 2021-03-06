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

package org.terasology.StaticCities.door;

import org.terasology.StaticCities.BlockTheme;
import org.terasology.StaticCities.DefaultBlockType;
import org.terasology.StaticCities.raster.Pen;
import org.terasology.StaticCities.raster.Pens;
import org.terasology.StaticCities.raster.RasterTarget;
import org.terasology.StaticCities.raster.RasterUtil;
import org.terasology.commonworld.heightmap.HeightMap;

/**
 * Converts {@link WingDoor} into blocks
 */
public class WingDoorRasterizer extends DoorRasterizer<WingDoor> {

    /**
     * @param theme the block theme to use
     */
    public WingDoorRasterizer(BlockTheme theme) {
        super(theme, WingDoor.class);
    }

    @Override
    public void raster(RasterTarget target, WingDoor door, HeightMap hm) {
        Pen pen = Pens.fill(target, door.getBaseHeight(), door.getTopHeight(), DefaultBlockType.WING_DOOR);
        RasterUtil.fillRect(pen, door.getArea());
    }

}
