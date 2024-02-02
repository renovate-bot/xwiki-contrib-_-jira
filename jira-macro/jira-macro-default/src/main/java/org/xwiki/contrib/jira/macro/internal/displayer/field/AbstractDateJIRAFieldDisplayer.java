/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.contrib.jira.macro.internal.displayer.field;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.jdom2.Element;
import org.xwiki.contrib.jira.macro.JIRAField;
import org.xwiki.contrib.jira.macro.JIRAMacroParameters;
import org.xwiki.rendering.block.Block;
import org.xwiki.rendering.block.VerbatimBlock;

/**
 * Common Field Displayer for Dates. Parses field in the JIRA date format and generates dates in the format
 * {@code dd-MMM-yyyy}.
 *
 * @version $Id$
 * @since 4.2M1
 */
public abstract class AbstractDateJIRAFieldDisplayer extends AbstractJIRAFieldDisplayer
{
    /**
     * JIRA Date format.
     */
    private DateFormat jiraDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

    /**
     * Date format for displaying.
     */
    private DateFormat displayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    @Override
    public List<Block> displayField(JIRAField field, Element issue, JIRAMacroParameters parameters)
    {
        List<Block> result;
        String date = getValue(field, issue);
        if (date != null) {
            try {
                Date parsedDate = this.jiraDateFormat.parse(date);
                result = Arrays.asList(new VerbatimBlock(this.displayDateFormat.format(parsedDate), true));
            } catch (ParseException e) {
                result = Arrays.asList(new VerbatimBlock(date, true));
            }
        } else {
            result = Collections.emptyList();
        }
        return result;
    }
}
