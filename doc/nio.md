Channel: This represents a stream of data between applications
Channel�������ֽڻ�������λ��ͨ����һ���ʵ�壨ͨ����һ���ļ����׽��֣�֮����Ч�ش������ݡ�

Buffer: This works with a channel to process data
Java NIO�е�Buffer���ں�NIOͨ�����н�����
д��Buffer -> Channel
����Channel -> Buffer

Selector: This is a technology that allows a single thread to handle multiple channels
Selector��ѡ��������Java NIO���ܹ����һ�����NIOͨ�������ܹ�֪��ͨ���Ƿ�Ϊ�����д�¼�����׼���������������һ���������߳̿��Թ�����channel���Ӷ��������������ӡ�


A channel and a buffer are typically associated with each other. Data may be transferred
from a channel to a buffer or from a buffer to a channel. The buffer, as its name implies, is
a temporary repository for information. The selector is useful in supporting application
scalability,

��Щ��Java NIO������Ҫ��ͨ����ʵ�֣�

    FileChannel
    DatagramChannel
    SocketChannel
    ServerSocketChannel
    
ʹ��Buffer��д���ݵĲ��裺
1. Write data into the Buffer
2. Call buffer.flip()
3. Read data out of the Buffer
4. Call buffer.clear() or buffer.compact()

clear()���������������������
compact()����ֻ������Ѿ����������ݡ��κ�δ�������ݶ����Ƶ�����������ʼ������д������ݽ��ŵ�������δ�����ݵĺ��档