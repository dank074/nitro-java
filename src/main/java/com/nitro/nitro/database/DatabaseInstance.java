package com.nitro.nitro.database;

import com.nitro.common.component.Component;
import io.ebean.Database;
import io.ebean.bean.EntityBean;

import java.util.concurrent.*;

public class DatabaseInstance extends Component implements IDatabaseInstance {

    private Database database;

    ScheduledExecutorService scheduler;
    ScheduledFuture<?> scheduled;
    ConcurrentLinkedQueue<EntityBean> queued;

    private boolean isProcessing;

    public DatabaseInstance(Database database) {
        this.database = database;

        this.scheduler = null;
        this.scheduled = null;
        this.queued = new ConcurrentLinkedQueue<>();

        this.isProcessing = false;
    }

    protected void onInit() {
        this.startProcessor();
    }

    protected void onDispose() {
        this.stopProcessor();
    }

    public void saveEntity(EntityBean entityBean, boolean skipQueue) {
        if(entityBean == null) return;

        if(skipQueue) {
            this.queued.remove(entityBean);

            try {
                this.getDatabase().save(entityBean);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }

            return;
        }

        if(this.queued.contains(entityBean)) return;

        this.queued.add(entityBean);
    }

    public void saveEntity(EntityBean entityBean) {
        this.saveEntity(entityBean, false);
    }

    private void startProcessor() {
        if(this.scheduler == null) {
            this.scheduler = Executors.newSingleThreadScheduledExecutor();
        }

        final DatabaseInstance that = this;

        final Runnable processor = new Runnable() {
            public void run() {
                if(that.isProcessing) return;

                that.isProcessing = true;

                while(true) {
                    try {
                        EntityBean entityBean = that.queued.poll();

                        if(entityBean == null) break;

                        that.getDatabase().save(entityBean);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());

                        break;
                    }
                }

                that.isProcessing = false;
            }
        };

        this.scheduled = this.scheduler.scheduleAtFixedRate(processor, 0, 10, TimeUnit.SECONDS);
    }

    private void stopProcessor() {
        if(this.scheduled == null) return;

        this.scheduled.cancel(false);

        this.scheduled = null;
    }

    public Database getDatabase() {
        return this.database;
    }
}
