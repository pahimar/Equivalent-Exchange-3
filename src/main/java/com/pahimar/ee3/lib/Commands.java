package com.pahimar.ee3.lib;

/**
 * Equivalent-Exchange-3
 * 
 * Commands
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Commands {

    /* Command related contants */
    public static final String ALL = "all";
    public static final String SELF = "self";
    public static final String ON = "on";
    public static final String OFF = "off";

    public static final String COMMAND_EE3 = "ee3";
    public static final String COMMAND_ON = "on";
    public static final String COMMAND_OFF = "off";
    public static final String COMMAND_ALL = "all";
    public static final String COMMAND_SELF = "self";
    public static final String COMMAND_OVERLAY = "overlay";
    public static final String COMMAND_POSITION = "position";
    public static final String COMMAND_OPACITY = "opacity";
    public static final String COMMAND_SCALE = "scale";
    public static final String COMMAND_TOP = "top";
    public static final String COMMAND_BOTTOM = "bottom";
    public static final String COMMAND_LEFT = "left";
    public static final String COMMAND_RIGHT = "right";
    public static final String COMMAND_PARTICLES = "particles";
    public static final String COMMAND_SOUNDS = "sounds";
    public static final String COMMAND_VERSION = "version";
    public static final String COMMAND_CHANGELOG = "changelog";
    public static final String COMMAND_EE3_USAGE = "ee3 [ overlay | particles | sounds | version ]";
    public static final String COMMAND_OVERLAY_USAGE = "ee3 overlay [ on | off | position | scale | opacity ]";
    public static final String COMMAND_OVERLAY_POSITION_USAGE = "ee3 overlay position [ top | bottom ] [ left | right ]";
    public static final String COMMAND_OVERLAY_OPACITY_USAGE = "ee3 overlay opacity ### ";
    public static final String COMMAND_OVERLAY_SCALE_USAGE = "ee3 overlay scale ### ";
    public static final String COMMAND_PARTICLES_USAGE = "ee3 particles [ on | off ]";
    public static final String COMMAND_SOUNDS_USAGE = "ee3 sounds [ all | self | off ]";
    public static final String COMMAND_VERSION_USAGE = "command.ee3.version.usage";

    public static final String COMMAND_OVERLAY_TURNED_ON = "command.ee3:overlay.turned_on";
    public static final String COMMAND_OVERLAY_TURNED_OFF = "command.ee3:overlay.turned_off";
    public static final String COMMAND_OVERLAY_POSITION_TOP_LEFT = "command.ee3:overlay.position.top_left";
    public static final String COMMAND_OVERLAY_POSITION_TOP_RIGHT = "command.ee3:overlay.position.top_right";
    public static final String COMMAND_OVERLAY_POSITION_BOTTOM_LEFT = "command.ee3:overlay.position.bottom_left";
    public static final String COMMAND_OVERLAY_POSITION_BOTTOM_RIGHT = "command.ee3:overlay.position.bottom_right";
    public static final String COMMAND_OVERLAY_OPACITY_USAGE_ADDITIONAL_TEXT = "command.ee3:overlay.opacity.usage.additional_text";
    public static final String COMMAND_OVERLAY_OPACITY_UPDATED = "command.ee3:overlay.opacity.updated";
    public static final String COMMAND_OVERLAY_SCALE_USAGE_ADDITIONAL_TEXT = "command.ee3:overlay.scale.usage.additional_text";
    public static final String COMMAND_OVERLAY_SCALE_UPDATED = "command.ee3:overlay.scale.updated";
    public static final String COMMAND_PARTICLES_TURNED_ON = "command.ee3:particles.turned_on";
    public static final String COMMAND_PARTICLES_TURNED_OFF = "command.ee3:particles.turned_off";
    public static final String COMMAND_SOUNDS_SET_TO_ALL = "command.ee3:sounds.set_to_all";
    public static final String COMMAND_SOUNDS_SET_TO_SELF = "command.ee3:sounds.set_to_self";
    public static final String COMMAND_SOUNDS_TURNED_OFF = "command.ee3:sounds.turned_off";
}
