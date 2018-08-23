import java.io.File;

public class Report {


    /**
     * 获取path对应文件的大小,返回GB
     *
     * @param path
     */
    public double diskReport(String path) {
        return (double)getdisk(path) / 1024 /1024 /1024;
    }

    /**
     * 获取path对应文件的大小,返回bytes
     *
     * @param path
     */
    public long getdisk(String path) {
        // 创建文件对象
        File file = new File(path);
        // 如果文件不存在返回-1
        if (!file.exists()) {
            System.out.println("file not exist");
            return -1;
        }
        // 记录大小总量
        long sum = 0;

        // 如果是文件夹，递归调用此方法
        if (file.isDirectory()) {
            // 遍历文件夹内所有文件和文件夹
            for (File f : file.listFiles()) {
                System.out.println(f);
                sum += getdisk(f.getAbsolutePath());
            }
        } else {
            return file.length();
        }

        // 返回size,单位为bytes
        return sum;
    }

    public static void main(String[] args) {
        double size = new Report().diskReport("");
        System.out.println(size);
    }

}
