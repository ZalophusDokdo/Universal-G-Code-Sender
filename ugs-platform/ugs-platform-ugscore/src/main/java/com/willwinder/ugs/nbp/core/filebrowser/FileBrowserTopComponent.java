/*
    Copywrite 2015-2016 Will Winder

    This file is part of Universal Gcode Sender (UGS).

    UGS is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UGS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UGS.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.willwinder.ugs.nbp.core.filebrowser;

import com.willwinder.ugs.nbp.lib.services.LocalizingService;
import com.willwinder.ugs.nbp.lookup.CentralLookup;
import com.willwinder.universalgcodesender.model.BackendAPI;
import com.willwinder.universalgcodesender.uielements.toolbars.FileBrowsePanel;
import java.awt.BorderLayout;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */

@TopComponent.Description(
        preferredID = "FileBrowserTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "middle_left", openAtStartup = false)
@ActionID(category = LocalizingService.FileBrowserCategory, id = LocalizingService.FileBrowserActionId)
@ActionReference(path = LocalizingService.FileBrowserWindowPath)
@TopComponent.OpenActionRegistration(
        displayName = "<Not localized:FileBrowserTopComponent>",
        preferredID = "FileBrowserTopComponent"
)
public final class FileBrowserTopComponent extends TopComponent {
    BackendAPI backend;
    
    public FileBrowserTopComponent() {
        backend = CentralLookup.getDefault().lookup(BackendAPI.class);
        this.setLayout(new BorderLayout());
        this.add(new FileBrowsePanel(backend), BorderLayout.CENTER);
    }

    @Override
    public void componentOpened() {
        setName(LocalizingService.FileBrowserTitle);
        setToolTipText(LocalizingService.FileBrowserTooltip);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
