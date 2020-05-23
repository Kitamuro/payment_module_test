package school.attractor.payment_module.domain.order;import com.querydsl.core.types.Predicate;import lombok.AllArgsConstructor;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Pageable;import org.springframework.stereotype.Service;import school.attractor.payment_module.domain.exception.OrderNotFound;import school.attractor.payment_module.domain.transaction.Transaction;import school.attractor.payment_module.domain.transaction.TransactionStatus;import java.util.Date;import java.util.List;import java.util.Optional;@Service@AllArgsConstructorpublic class OrderService {    public OrderRepository orderRepository;    public Page<Order> getOrders(Pageable pageable) {        return orderRepository.findAll(pageable);    }    public OrderDTO findByOrderId(int id) {        Optional<Order> order = orderRepository.findById(id);        return OrderDTO.from(order.orElseThrow( OrderNotFound::new));    }    public Order save (OrderDTO orderDTO) {        Order order = Order.from(orderDTO);        order.setDate ( new Date (  ) );        return orderRepository.save(order);    }    public void setOrderParam(Order order, Transaction transaction, String internalReferenceNumber, String retrievalReferenceNumber) {        switch (transaction.getType ()){            case HOLD:                order.setStatus ( TransactionStatus.RESERVED );                order.setInternalReferenceNumber ( internalReferenceNumber );                order.setRetrievalReferenceNumber ( retrievalReferenceNumber);                orderRepository.save ( order );                break;            case PAYMENT:                order.setInternalReferenceNumber ( internalReferenceNumber );                order.setRetrievalReferenceNumber ( retrievalReferenceNumber);                order.setStatus ( TransactionStatus.APPROVED );                orderRepository.save ( order );            case AUTH:                order.setStatus ( TransactionStatus.APPROVED );                orderRepository.save ( order );                break;            default:                if(transaction.getAmount () == order.getAmount ()){                    order.setStatus ( TransactionStatus.TOTAL_REFUND);                    orderRepository.save ( order );                }else {                    order.setStatus ( TransactionStatus.PARTIAL_REFUND );                    order.setResidual ( order.getResidual () - transaction.getAmount () );                    orderRepository.save ( order );                }                break;        }    }    public void change(Order order) {        orderRepository.save(order);    }    public Order findById(int orderId){        return orderRepository.findById ( orderId ).get ();    }    public Page<Order> getSearchOrders(Pageable pageable, Predicate predicate) {        return orderRepository.findAll ( predicate, pageable );    }    Integer getTotalAmountSum(Page<Order> orders) {        List<Order> orderList = orders.getContent();        Integer total = 0;        for (Order order : orderList) {            total += order.getAmount();        }        return total;    }    Integer getTotalQuantity(Page<Order> orders) {        List<Order> orderList = orders.getContent();        return orderList.size();    }}