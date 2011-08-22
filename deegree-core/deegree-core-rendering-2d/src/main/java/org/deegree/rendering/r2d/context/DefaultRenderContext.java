//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2010 by:
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
package org.deegree.rendering.r2d.context;

import static javax.imageio.ImageIO.write;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import org.deegree.rendering.r2d.Java2DRasterRenderer;
import org.deegree.rendering.r2d.Java2DRenderer;
import org.deegree.rendering.r2d.Java2DTextRenderer;
import org.deegree.rendering.r2d.RasterRenderer;
import org.deegree.rendering.r2d.Renderer;
import org.deegree.rendering.r2d.TextRenderer;
import org.deegree.style.utils.ImageUtils;

/**
 * 
 * @author <a href="mailto:schmitz@lat-lon.de">Andreas Schmitz</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
public class DefaultRenderContext implements RenderContext {

    private BufferedImage image;

    private Graphics2D graphics;

    private Java2DRenderer renderer;

    private Java2DTextRenderer textRenderer;

    private Java2DRasterRenderer rasterRenderer;

    private OutputStream out;

    private String format;

    public DefaultRenderContext( RenderingInfo info ) {
        format = info.getFormat();
        image = ImageUtils.prepareImage( format, info.getWidth(), info.getHeight(), info.getTransparent(),
                                         info.getBgColor() );
        graphics = image.createGraphics();
        renderer = new Java2DRenderer( graphics, info.getWidth(), info.getHeight(), info.getEnvelope(),
                                       info.getPixelSize() );
        textRenderer = new Java2DTextRenderer( renderer );
        rasterRenderer = new Java2DRasterRenderer( graphics );
    }

    @Override
    public Renderer getVectorRenderer() {
        return renderer;
    }

    @Override
    public TextRenderer getTextRenderer() {
        return textRenderer;
    }

    @Override
    public RasterRenderer getRasterRenderer() {
        return rasterRenderer;
    }

    @Override
    public void setOutput( OutputStream out ) {
        this.out = out;
    }

    @Override
    public boolean close()
                            throws IOException {
        try {
            graphics.dispose();
            if ( out != null ) {
                String format = this.format.substring( this.format.indexOf( "/" ) + 1 );
                if ( format.equals( "x-ms-bmp" ) ) {
                    format = "bmp";
                }
                if ( format.equals( "png; subtype=8bit" ) || format.equals( "png; mode=8bit" ) ) {
                    format = "png";
                }
                return write( image, format, out );
            }
        } finally {
            closeQuietly( out );
        }
        return false;
    }

    @Override
    public void paintImage( BufferedImage img ) {
        graphics.drawImage( img, 0, 0, null );
    }

}