package views;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class MenuList {
    JTextArea output;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem, menuLogout;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;
    Window window;

    public JMenuBar createMenuBar(Window window) {
    	this.window = window;

        ListenForButton lForButton = new ListenForButton();

        
        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("Reload");
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuItem = new JMenuItem("Add Event");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Add Calender");
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuLogout = new JMenuItem("Log out");
        menu.add(menuLogout);
        menuLogout.addActionListener(lForButton);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("Submenu");

        menuItem = new JMenuItem("Submenu");
        submenu.add(menuItem);

        menuItem = new JMenuItem("Submenu2");
        submenu.add(menuItem);
        
        menu.add(submenu);

        return menuBar;
    }
    private class ListenForButton implements ActionListener {

		// This method is called when an event occurs

		public void actionPerformed(ActionEvent e) {

			// Check if the source of the event was the button

			if (e.getSource() == menuLogout) {
				window.getLoginPage();
			}
		}
	}
}
