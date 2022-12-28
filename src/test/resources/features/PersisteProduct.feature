Feature: Persist product in database by gRPC request

  Scenario: Persist product
    Given that a requisition with product has been received
    When the request via gRPC is handled
    Then it must be written to the database