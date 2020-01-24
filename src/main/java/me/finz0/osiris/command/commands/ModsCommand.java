package me.finz0.osiris.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.finz0.osiris.command.Command;
import me.finz0.osiris.module.Module;
import me.finz0.osiris.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

//Credit: Seth - SeppukuDevelopment
public class ModsCommand extends Command {
    @Override
    public String getAlias() {
        return "modules";
    }

    @Override
    public String getSyntax() {
        return "modules";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        /*
        for(Module m : ModuleManager.getModules()){
            if(m.isEnabled()) {
                Command.sendClientMessage(ChatFormatting.GREEN + m.getName());
            } else {
                Command.sendClientMessage(ChatFormatting.RED + m.getName());
            }
        }
         */

        final int size = ModuleManager.getModules().size();

        final TextComponentString msg = new TextComponentString("\2477Modules: " + "\247f ");

        for (int i = 0; i < size; i++) {
            final Module mod = ModuleManager.getModules().get(i);
            if (mod != null) {
                msg.appendSibling(new TextComponentString((mod.isEnabled() ? "\247a" : "\247c") + mod.getName() + "\2477" + ((i == size - 1) ? "" : ", "))
                        .setStyle(new Style()
                                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(mod.getCategory().toString())))
                                .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command.getPrefix() + "toggle" + " " + mod.getName()))));
            }
        }

        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(msg);
    }
}
