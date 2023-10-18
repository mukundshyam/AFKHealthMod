package net.mukundshyam.afkhealth.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mukundshyam.afkhealth.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ActivatorItem extends Item{
    public ActivatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        assert player != null;
        BlockPos pos = context.getBlockPos();
        final int[] health = {(int) player.getHealth()};
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                health[0] = (int) player.getHealth();
                if (health[0] == 20) {
                    player.sendMessage(Text.literal("Keeping track of health..."), true);
                }
                else {
                    player.sendMessage(Text.literal("Low Health!"), true);
                    context.getWorld().playSound(null, pos, ModSounds.LOW_HEALTH, SoundCategory.AMBIENT, 3f, 1f);
                    executor.shutdown();
                }
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.afkhealth.activator.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}