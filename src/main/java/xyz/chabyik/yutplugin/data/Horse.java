package xyz.chabyik.yutplugin.data;

import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;

public class Horse extends MetadataValueAdapter {
    public Horse(Plugin owningPlugin) {
        super(owningPlugin);
    }

    @Override
    public Object value() {
        return null;
    }

    @Override
    public void invalidate() {}
}
