//
// Autogenerated by Scrooge
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//
import Foundation
import TwitterApacheThrift
public struct SubobjectThriftStruct: Hashable {
    public var value: ThriftStruct?
    public var intValue: Int16
    enum CodingKeys: Int, CodingKey {
        case value = 1
        case intValue = 2
    }
    public init(value: ThriftStruct? = nil, intValue: Int16) {
        self.value = value
        self.intValue = intValue
    }
}
extension SubobjectThriftStruct: ThriftCodable {}
