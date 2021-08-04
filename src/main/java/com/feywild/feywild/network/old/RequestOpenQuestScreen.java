package com.feywild.feywild.network.old;

import com.feywild.feywild.quest.old.Quest;
import com.feywild.feywild.quest.old.QuestMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestOpenQuestScreen {

   ResourceLocation quest;
   int id;
    //Read msg from buf
    public RequestOpenQuestScreen(PacketBuffer buf) {
       quest = buf.readResourceLocation();
       id = buf.readInt();
    }

    //constructor
    public RequestOpenQuestScreen(ResourceLocation quest, int id) {
        this.quest = quest;
    }

    //Save msg to buf
    public void toBytes(PacketBuffer buf) {
        buf.writeResourceLocation(quest);
        buf.writeInt(id);
    }

    //handle package data
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork( () -> {
            try{
                if(ctx.get().getDirection().getReceptionSide().isServer()) {
                    PlayerEntity playerEntity = ctx.get().getSender();
                    Quest quest1 = QuestMap.getQuest(quest.toString());
//                    FeywildNetwork.sendToPlayer(new OpenQuestScreen(Collections.singletonList(new MessageQuest(quest1.getId(),quest1.getText(),quest1.getName(),quest1.getIcon(),quest1.canSkip())), id),playerEntity);
                    ctx.get().setPacketHandled(true);
                }
            }catch (Exception e) {
                ctx.get().setPacketHandled(false);
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
