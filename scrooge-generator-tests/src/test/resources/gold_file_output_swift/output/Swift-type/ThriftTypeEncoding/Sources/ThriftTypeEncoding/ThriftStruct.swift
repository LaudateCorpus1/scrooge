//
// Autogenerated by Scrooge
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//
import Foundation
import TwitterApacheThrift
public struct ThriftStruct: Hashable {
    public var intValue: Int32
    public var unionValue: ThriftUnion
    enum CodingKeys: Int, CodingKey {
        case intValue = 1
        case unionValue = 2
    }
    public init(intValue: Int32, unionValue: ThriftUnion) {
        self.intValue = intValue
        self.unionValue = unionValue
    }
}
extension ThriftStruct: ThriftCodable {}
