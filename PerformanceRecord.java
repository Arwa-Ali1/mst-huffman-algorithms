package g22_Code;

// Stores performance measurement for each encoding test
public class PerformanceRecord {
    String fileName;
    String method;
    long originalSize;
    long compressedSize;
    double compressionRatio;
    long encodeTime;
    long decodeTime;

    public PerformanceRecord(String fileName, String method, long originalSize, long compressedSize,
                             long encodeTime, long decodeTime) {
        this.fileName = fileName;
        this.method = method;
        this.originalSize = originalSize;
        this.compressedSize = compressedSize;
        this.compressionRatio = (double) compressedSize / originalSize;
        this.encodeTime = encodeTime;
        this.decodeTime = decodeTime;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%d\t%d\t%.3f\t%d\t%d",
                fileName, method, originalSize, compressedSize, compressionRatio, encodeTime, decodeTime);
    }
}
