//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2011 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.protocol.ows.metadata.domain;

import java.util.List;

/**
 * {@link PossibleValues} that are defined by a list of all valid values and/or ranges of values.
 * <p>
 * Data model has been designed to capture the expressiveness of all OWS specifications and versions and was verified
 * against the following specifications:
 * <ul>
 * <li>OWS Common 2.0</li>
 * </ul>
 * </p>
 * <p>
 * From OWS Common 2.0: <cite>List of all the valid values and/or ranges of values for this quantity. For numeric
 * quantities, signed values should be ordered from negative infinity to positive infinity.</cite>
 * </p>
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
public class AllowedValues implements PossibleValues {

    private List<Values> values;

    /**
     * Creates a new {@link AllowedValues} instance.
     * 
     * @param values
     *            list of all valid values and/or ranges of values for this quantity, may be <code>null</code>
     */
    public AllowedValues( List<Values> values ) {
        this.values = values;
    }

    /**
     * Returns the list of all valid values and/or ranges of values for this quantity.
     * <p>
     * From OWS Common 2.0: <cite>List of all the valid values and/or ranges of values for this quantity. For numeric
     * quantities, signed values should be ordered from negative infinity to positive infinity.</cite>
     * </p>
     * 
     * @return list of all valid values and/or ranges of values for this quantity, may be <code>null</code>
     */
    public List<Values> getValues() {
        return values;
    }

    /**
     * Sets the list of all valid values and/or ranges of values for this quantity.
     * 
     * @param values
     *            list of all valid values and/or ranges of values for this quantity, may be <code>null</code>
     */
    public void setValues( List<Values> values ) {
        this.values = values;
    }
}
