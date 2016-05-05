Buffer: This works with a channel to process data

Java NIO中的Buffer用于和NIO通道进行交互。
写：Buffer -> Channel
读：Channel -> Buffer

使用Buffer读写数据的步骤：
1. Write data into the Buffer
2. Call buffer.flip()
3. Read data out of the Buffer
4. Call buffer.clear() or buffer.compact()

clear()方法会清空整个缓冲区。
compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。