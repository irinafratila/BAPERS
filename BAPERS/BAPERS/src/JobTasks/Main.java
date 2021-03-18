//package JobTasks;
//
//public class Main {
//    public static void main(String[] args) {
//        Job job = new Job(1,"nothing");
//        job.getStartTime();
//        System.out.println(job.getStartTime());
//        System.out.println(job.setDeadline());
//        new java.util.Timer().schedule(
//                new java.util.TimerTask() {
//                    @Override
//                    public void run() {
//                        job.completeJob();
//                        job.setTimeTaken();
//                        System.out.println(job.getTimeTaken());
//                    }
//                },
//                5000
//        );
//
//    }
//}