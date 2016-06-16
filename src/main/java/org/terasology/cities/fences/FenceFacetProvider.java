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

package org.terasology.cities.fences;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.terasology.cities.common.Edges;
import org.terasology.cities.parcels.Parcel;
import org.terasology.cities.parcels.ParcelFacet;
import org.terasology.cities.parcels.Zone;
import org.terasology.commonworld.Orientation;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector2i;
import org.terasology.world.generation.*;

import java.util.Optional;

/**
 * Produces an empty {@link FenceFacet}.
 */
@Produces(FenceFacet.class)
@Requires(@Facet(ParcelFacet.class))
public class FenceFacetProvider implements FacetProvider {

    private final LoadingCache<Parcel, Optional<SimpleFence>> cache = CacheBuilder.newBuilder().build(
            new CacheLoader<Parcel, Optional<SimpleFence>>() {

        @Override
        public Optional<SimpleFence> load(Parcel parcel) {
            return generateFence(parcel);
        }
    });


    private long seed;

    @Override
    public void setSeed(long seed) {
        this.seed = seed;
    }

    @Override
    public void process(GeneratingRegion region) {

        Border3D border = region.getBorderForFacet(FenceFacet.class);
        FenceFacet facet = new FenceFacet(region.getRegion(), border);

        ParcelFacet parcelFacet = region.getRegionFacet(ParcelFacet.class);

        for (Parcel parcel : parcelFacet.getParcels()) {
            Optional<SimpleFence> optFence = cache.getUnchecked(parcel);
            if (optFence.isPresent()) {
                facet.addFence(optFence.get());
            }
        }

        region.setRegionFacet(FenceFacet.class, facet);
    }

    private Optional<SimpleFence> generateFence(Parcel parcel) {

        if (parcel.getZone() == Zone.RESIDENTIAL) {
            Rect2i fenceRc = Rect2i.createFromMinAndMax(parcel.getShape().min(), parcel.getShape().max());
            Orientation gateOrient = parcel.getOrientation();
            Vector2i gatePos = Edges.getCorner(fenceRc, parcel.getOrientation());
            return Optional.of(new SimpleFence(fenceRc, gateOrient, gatePos));
        } else {
            return Optional.empty();
        }
    }

}
