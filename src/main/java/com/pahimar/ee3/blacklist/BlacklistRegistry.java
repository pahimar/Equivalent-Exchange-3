package com.pahimar.ee3.blacklist;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.util.LoaderHelper;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import static com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy.Blacklist;
import static com.pahimar.ee3.api.event.BlacklistEvent.*;

// TODO Logging
public class BlacklistRegistry {

    public static final BlacklistRegistry INSTANCE = new BlacklistRegistry();

    private static final Marker BLACKLIST_MARKER = MarkerManager.getMarker("EE3_BLACKLIST", LogHelper.MOD_MARKER);
    private static final Marker KNOWLEDGE_BLACKLIST_MARKER = MarkerManager.getMarker("EE3_BLACKLIST_KNOWLEDGE", BLACKLIST_MARKER);
    private static final Marker KNOWLEDGE_WHITELIST_MARKER = MarkerManager.getMarker("EE3_WHITELIST_KNOWLEDGE", BLACKLIST_MARKER);
    private static final Marker EXCHANGE_BLACKLIST_MARKER = MarkerManager.getMarker("EE3_BLACKLIST_EXCHANGE", BLACKLIST_MARKER);
    private static final Marker EXCHANGE_WHITELIST_MARKER = MarkerManager.getMarker("EE3_WHITELIST_EXCHANGE", BLACKLIST_MARKER);

    private final Set<WrappedStack> knowledgeBlacklist, exchangeBlacklist;
    public static File knowledgeBlacklistFile, exchangeBlacklistFile;
    private transient boolean loadedFromServer;

    /**
     * TODO Finish JavaDoc
     */
    private BlacklistRegistry() {

        knowledgeBlacklist = new TreeSet<>();
        exchangeBlacklist = new TreeSet<>();
        loadedFromServer = false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public Set<WrappedStack> getKnowledgeBlacklist() {
        return Collections.unmodifiableSet(knowledgeBlacklist);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public Set<WrappedStack> getExchangeBlacklist() {
        return Collections.unmodifiableSet(exchangeBlacklist);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @return
     */
    public boolean isLearnable(Object object) {

        if (WrappedStack.canBeWrapped(object)) {

            WrappedStack wrappedObject = WrappedStack.wrap(object, 1);

            if (object instanceof ItemStack && ((ItemStack) object).isItemDamaged()) {
                return false;
            }
            else {
                return !knowledgeBlacklist.contains(wrappedObject) && EnergyValueRegistryProxy.hasEnergyValue(wrappedObject);
            }
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @return
     */
    public boolean isExchangeable(Object object) {

        if (WrappedStack.canBeWrapped(object)) {

            WrappedStack wrappedStack = WrappedStack.wrap(object, 1);
            return !exchangeBlacklist.contains(wrappedStack) && EnergyValueRegistryProxy.hasEnergyValue(wrappedStack);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param blacklist
     */
    public void addToBlacklist(Object object, Blacklist blacklist) {

        if (WrappedStack.canBeWrapped(object)) {

            WrappedStack wrappedStack = WrappedStack.wrap(object, 1);

            if (blacklist == Blacklist.KNOWLEDGE) {
                if (wrappedStack != null && !MinecraftForge.EVENT_BUS.post(new KnowledgeBlacklistEvent(object))) {
                    LogHelper.trace(KNOWLEDGE_BLACKLIST_MARKER, "[{}] Mod with ID '{}' added object {} to the player knowledge blacklist", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack);
                    knowledgeBlacklist.add(WrappedStack.wrap(object, 1));
                    save(blacklist);
                }
            }
            else if (blacklist == Blacklist.EXCHANGE) {
                if (wrappedStack != null && !MinecraftForge.EVENT_BUS.post(new ExchangeBlacklistEvent(object))) {
                    LogHelper.trace(EXCHANGE_BLACKLIST_MARKER, "[{}] Mod with ID '{}' added object {} to the exchange blacklist", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack);
                    exchangeBlacklist.add(WrappedStack.wrap(object, 1));
                    save(blacklist);
                }
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param object
     * @param blacklist
     */
    public void removeFromBlacklist(Object object, Blacklist blacklist) {

        if (WrappedStack.canBeWrapped(object)) {

            WrappedStack wrappedStack = WrappedStack.wrap(object, 1);

            if (blacklist == Blacklist.KNOWLEDGE) {
                if (wrappedStack != null && !MinecraftForge.EVENT_BUS.post(new KnowledgeWhitelistEvent(object))) {
                    LogHelper.trace(KNOWLEDGE_WHITELIST_MARKER, "[{}] Mod with ID '{}' removed object {} from the player knowledge blacklist", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack);
                    knowledgeBlacklist.remove(wrappedStack);
                    save(blacklist);
                }
            }
            else if (blacklist == Blacklist.EXCHANGE) {
                if (wrappedStack != null && !MinecraftForge.EVENT_BUS.post(new ExchangeWhitelistEvent(object))) {
                    LogHelper.trace(EXCHANGE_WHITELIST_MARKER, "[{}] Mod with ID '{}' removed object {} from the exchange blacklist", LoaderHelper.getLoaderState(), Loader.instance().activeModContainer().getModId(), wrappedStack);
                    exchangeBlacklist.remove(wrappedStack);
                    save(blacklist);
                }
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     */
    public void load() {

        LogHelper.trace(BLACKLIST_MARKER, "Loading player knowledge blacklist from {}", knowledgeBlacklistFile.getAbsolutePath());
        knowledgeBlacklist.clear();
        knowledgeBlacklist.addAll(SerializationHelper.readSetFromFile(knowledgeBlacklistFile));

        LogHelper.trace(BLACKLIST_MARKER, "Loading exchange blacklist from {}", exchangeBlacklistFile.getAbsolutePath());
        exchangeBlacklist.clear();
        exchangeBlacklist.addAll(SerializationHelper.readSetFromFile(exchangeBlacklistFile));
    }

    public void load(Set<WrappedStack> blacklistSet, Blacklist blacklist) {

        if (blacklist != null && blacklistSet != null) {

            loadedFromServer = true;

            if (blacklist == Blacklist.KNOWLEDGE) {
                LogHelper.info("Received {} player knowledge blacklist entries from server", blacklistSet.size());
                knowledgeBlacklist.clear();
                knowledgeBlacklist.addAll(blacklistSet);
            }
            else if (blacklist == Blacklist.EXCHANGE) {
                LogHelper.info("Received {} exchange blacklist entries from server", blacklistSet.size());
                exchangeBlacklist.clear();
                exchangeBlacklist.addAll(blacklistSet);
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param blacklist
     */
    public void save(Blacklist blacklist) {

        if (!loadedFromServer) {
            if (blacklist == Blacklist.KNOWLEDGE) {
                LogHelper.trace(BLACKLIST_MARKER, "Saving player knowledge blacklist to {}", knowledgeBlacklistFile.getAbsolutePath());
                SerializationHelper.writeJsonFile(knowledgeBlacklistFile, SerializationHelper.GSON.toJson(knowledgeBlacklist));
            }
            else if (blacklist == Blacklist.EXCHANGE) {
                LogHelper.trace(BLACKLIST_MARKER, "Saving exchange blacklist to {}", exchangeBlacklistFile.getAbsolutePath());
                SerializationHelper.writeJsonFile(exchangeBlacklistFile, SerializationHelper.GSON.toJson(exchangeBlacklist));
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     */
    public void saveAll() {

        if (!loadedFromServer) {
            LogHelper.trace(BLACKLIST_MARKER, "Saving all blacklists to disk", exchangeBlacklistFile.getAbsolutePath());
            SerializationHelper.writeJsonFile(knowledgeBlacklistFile, SerializationHelper.GSON.toJson(knowledgeBlacklist));
            SerializationHelper.writeJsonFile(exchangeBlacklistFile, SerializationHelper.GSON.toJson(exchangeBlacklist));
        }
    }
}
